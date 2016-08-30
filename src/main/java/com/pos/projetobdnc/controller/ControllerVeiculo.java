/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.controller;

import com.pos.projetobdnc.entity.Aluguel;
import com.pos.projetobdnc.entity.Veiculo;
import com.pos.projetobdnc.enums.TipoVeiculo;
import com.pos.projetobdnc.service.ServiceVeiculo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author kaiqu
 */
@ManagedBean
@SessionScoped
public class ControllerVeiculo {

    @EJB
    private ServiceVeiculo serviceVeiculo;

    private Veiculo veiculo;
    private Veiculo veiculoPesquisado;
    private Aluguel aluguel;
    private List<Veiculo> listaVeiculos;
    private UploadedFile fileVeiculo;
    private String chegada, saida;

    public ControllerVeiculo() {
        this.veiculo = new Veiculo();
        this.veiculo.setTipo(TipoVeiculo.ECONOMICO);
        this.veiculoPesquisado = new Veiculo();
        this.aluguel = new Aluguel();
        this.listaVeiculos = new ArrayList<>();
        this.chegada = "";
        this.saida = "";
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Veiculo getVeiculoPesquisado() {
        return veiculoPesquisado;
    }

    public void setVeiculoPesquisado(Veiculo veiculoPesquisado) {
        this.veiculoPesquisado = veiculoPesquisado;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public UploadedFile getFileVeiculo() {
        return fileVeiculo;
    }

    public void setFileVeiculo(UploadedFile fileVeiculo) {
        this.fileVeiculo = fileVeiculo;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listVeiculos) {
        this.listaVeiculos = listVeiculos;
    }

    public String getChegada() {
        return chegada;
    }

    public void setChegada(String chegada) {
        this.chegada = chegada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    //Metodos da aplicação
    public void carregarListaDeVeiculos() {
        this.setListaVeiculos(serviceVeiculo.listar());
    }

    public void carregarListaDeVeiculosEconomicos() {
        List<Veiculo> listar = serviceVeiculo.listar();
        List<Veiculo> veiculosEconomicos = new ArrayList<>();
        for (Veiculo v : listar) {
            if (v.getTipo().equals(TipoVeiculo.ECONOMICO)) {
                veiculosEconomicos.add(v);
            }
        }
        this.setListaVeiculos(veiculosEconomicos);
    }

    public void carregarListaDeVeiculosLuxo() {
        List<Veiculo> listar = serviceVeiculo.listar();
        List<Veiculo> veiculosLuxo = new ArrayList<>();
        for (Veiculo v : listar) {
            if (v.getTipo().equals(TipoVeiculo.LUXO)) {
                veiculosLuxo.add(v);
            }
        }
        this.setListaVeiculos(veiculosLuxo);
    }

    public void carregarListaDeVeiculosSuv() {
        List<Veiculo> listar = serviceVeiculo.listar();
        List<Veiculo> veiculosSuv = new ArrayList<>();
        for (Veiculo v : listar) {
            if (v.getTipo().equals(TipoVeiculo.SUV)) {
                veiculosSuv.add(v);
            }
        }
        this.setListaVeiculos(veiculosSuv);
    }

    public String addVeiculo() throws IOException {
        veiculo.setAluguel(aluguel);
        veiculo.setFoto(upload(this.fileVeiculo));
        serviceVeiculo.salvar(veiculo);
        veiculo = new Veiculo();
        aluguel = new Aluguel();
        return "";
    }

    public String pesquisarVeiculo(long id) {
        veiculoPesquisado = serviceVeiculo.pesquisar(Veiculo.class, id);
        return "itemPage.xhtml";
    }

    public String upload(UploadedFile fileUp) {
        if (fileUp != null) {
            try {
                File targetFolder = new File("C:\\Users\\kaiqu\\OneDrive\\Documentos\\NetBeansProjectsLocal\\ProjetoBDNC-2\\src\\main\\webapp\\img\\veiculos");

                System.out.println("Aqui target folder " + targetFolder);

                InputStream inputStream = fileUp.getInputstream();
                String tipoArquivo = fileUp.getFileName();
                tipoArquivo = tipoArquivo.substring(tipoArquivo.lastIndexOf("."), tipoArquivo.length());
                String nomeArquivo = veiculo.getPlaca() + tipoArquivo;

                System.out.println("Nome do arquivo" + nomeArquivo);

                OutputStream out = new FileOutputStream(new File(targetFolder, nomeArquivo));
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputStream.close();
                out.flush();
                out.close();

                System.out.println("Sair");
                return nomeArquivo;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String locarVeiculo() {
        return "";
    }

    public void valorAluguel() {
        String[] splitChegada = this.chegada.split("/");
        int diaChegada = Integer.parseInt(splitChegada[0]);
        int mesChegada = Integer.parseInt(splitChegada[1]);
        int anoChegada = Integer.parseInt(splitChegada[2]);

        String[] splitSaida = this.saida.split("/");
        int diaSaida = Integer.parseInt(splitSaida[0]);
        int mesSaida = Integer.parseInt(splitSaida[1]);
        int anoSaida = Integer.parseInt(splitSaida[2]);

        Calendar chegada = Calendar.getInstance();
        chegada.set(anoChegada, mesChegada, diaChegada);
        System.out.println(chegada.get(Calendar.DAY_OF_YEAR));

        Calendar saida = Calendar.getInstance();
        saida.set(anoSaida, mesSaida, diaSaida);
        System.out.println(saida.get(Calendar.DAY_OF_YEAR));

    }
}
