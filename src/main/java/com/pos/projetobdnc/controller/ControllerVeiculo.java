/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.controller;

import com.pos.projetobdnc.dao.poliglota.AluguelDaoMongodb;
import com.pos.projetobdnc.dao.poliglota.AluguelDaoNeo4j;
import com.pos.projetobdnc.entity.Aluguel;
import com.pos.projetobdnc.entity.Cliente;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
    private List<Veiculo> veiculosQueForamAlugados;
    private UploadedFile fileVeiculo;
    private String chegada, saida;
    private int tipoVeiculo;

    public ControllerVeiculo() {
        this.veiculo = new Veiculo();
        this.veiculo.setTipo(TipoVeiculo.ECONOMICO);
        this.veiculoPesquisado = new Veiculo();
        this.aluguel = new Aluguel();
        this.listaVeiculos = new ArrayList<>();
        this.veiculosQueForamAlugados = new ArrayList<>();
        this.chegada = "";
        this.saida = "";
        this.tipoVeiculo = 10;
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

    public int getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(int tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public List<Veiculo> getVeiculosQueForamAlugados() {
        return veiculosQueForamAlugados;
    }

    public void setVeiculosQueForamAlugados(List<Veiculo> veiculosQueForamAlugados) {
        this.veiculosQueForamAlugados = veiculosQueForamAlugados;
    }

    //Metodos da aplicação
    public void carregarListaDeVeiculos() {
        this.setListaVeiculos(serviceVeiculo.listar());
        listaVeiculos.stream().forEach((listaVeiculo) -> {
            System.out.println(listaVeiculo.toString());
        });
    }

    public void carregarListaDeVeiculosEconomicos() {
        List<Veiculo> listar = serviceVeiculo.listar();
        List<Veiculo> veiculosEconomicos = new ArrayList<>();
        listar.stream().filter((v) -> (v.getTipo().equals(TipoVeiculo.ECONOMICO))).forEach((v) -> {
            veiculosEconomicos.add(v);
        });
        this.setListaVeiculos(veiculosEconomicos);
    }

    public void carregarListaDeVeiculosLuxo() {
        List<Veiculo> listar = serviceVeiculo.listar();
        List<Veiculo> veiculosLuxo = new ArrayList<>();
        listar.stream().filter((v) -> (v.getTipo().equals(TipoVeiculo.LUXO))).forEach((v) -> {
            veiculosLuxo.add(v);
        });
        this.setListaVeiculos(veiculosLuxo);
    }

    public void carregarListaDeVeiculosSuv() {
        List<Veiculo> listar = serviceVeiculo.listar();
        List<Veiculo> veiculosSuv = new ArrayList<>();
        listar.stream().filter((v) -> (v.getTipo().equals(TipoVeiculo.SUV))).forEach((v) -> {
            veiculosSuv.add(v);
        });
        this.setListaVeiculos(veiculosSuv);
    }

    public void addVeiculo() throws IOException {
        veiculo.setAluguel(aluguel);
        veiculo.setFoto(upload(this.fileVeiculo));
        this.verificaTipoVeiculo();
        serviceVeiculo.salvar(veiculo);
        veiculo = new Veiculo();
        aluguel = new Aluguel();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void verificaTipoVeiculo() {
        if (this.tipoVeiculo == 0) {
            this.veiculo.setTipo(TipoVeiculo.ECONOMICO);
        } else if (this.tipoVeiculo == 1) {
            this.veiculo.setTipo(TipoVeiculo.SUV);
        } else {
            this.veiculo.setTipo(TipoVeiculo.LUXO);
        }
    }

    public String pesquisarVeiculo(long id) {
        veiculoPesquisado = serviceVeiculo.pesquisar(Veiculo.class, id);
        return "itemPage.xhtml";
    }

    public String pesquisarVeiculoMinhaLocacao(long id) {
        veiculoPesquisado = serviceVeiculo.pesquisar(Veiculo.class, id);
        return "devolucaoDeVeiculo.xhtml";
    }

    public String valorTotalDaLocacao() {
        int chegada1 = this.veiculoPesquisado.getAluguel().getChegada();
        int saida1 = this.veiculoPesquisado.getAluguel().getSaida();
        int valor = (int) this.veiculoPesquisado.getAluguel().getValor();

        int soma = (chegada1 - saida1) * valor;

        System.out.println(soma);

        return (String.valueOf(soma));
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

    public void devolverVeiculo() {
        this.veiculoPesquisado.getAluguel().setChegada(0);
        this.veiculoPesquisado.getAluguel().setSaida(0);
        this.veiculoPesquisado.getAluguel().setCliente(null);
        serviceVeiculo.atualizar(veiculoPesquisado);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void locarCarro(Cliente cliente) {
        System.out.println("Entrou no metodo de Locar");
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
        int getchegada = chegada.get(Calendar.DAY_OF_YEAR);
        System.out.println(getchegada);

        Calendar saida = Calendar.getInstance();
        saida.set(anoSaida, mesSaida, diaSaida);
        int getSaida = saida.get(Calendar.DAY_OF_YEAR);
        System.out.println(getSaida);
        
        System.out.println("Vai atualizar os dados do Aluguel do Veiculo");
        
        Veiculo veiculoP = serviceVeiculo.pesquisar(Veiculo.class, this.veiculoPesquisado.getId());
        veiculoP.getAluguel().setChegada(getchegada);
        veiculoP.getAluguel().setSaida(getSaida);
        veiculoP.getAluguel().setCliente(cliente);
        veiculoP.getAluguel().setIdVeiculo(veiculoP.getId());
        
        System.out.println("Veiculo Atualizado");
        System.out.println(cliente);
        System.out.println(veiculoP);

        serviceVeiculo.atualizar(veiculoP);

        new AluguelDaoMongodb().create(veiculoP.getAluguel());

        new AluguelDaoNeo4j().create(veiculoP);
        
        this.aluguel = new Aluguel();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String retornarRelatorioVeiculosAlugados() {
        this.carregarListaDeVeiculosAlugados();
        return "relatorioCarrosAlugados.xhtml";
    }

    public void carregarListaDeVeiculosAlugados() {
        List<Aluguel> listAll = new AluguelDaoMongodb().listAll();
        List<Veiculo> veiculos = new ArrayList<>();
        listAll.stream().forEach((a) -> {
            System.out.println(a.toString());
            veiculos.add(serviceVeiculo.pesquisar(Veiculo.class, a.getIdVeiculo()));
        });
        this.setVeiculosQueForamAlugados(veiculos);
    }
}
