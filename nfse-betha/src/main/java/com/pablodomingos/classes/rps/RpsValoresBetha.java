package com.pablodomingos.classes.rps;

import com.pablodomingos.classes.rps.builders.ValoresBuilder;
import com.pablodomingos.classes.rps.enums.IssRetido;
import com.pablodomingos.util.DoubleUtil;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Transient;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class RpsValoresBetha extends AbstractRPS {

  @Element(name = "ValorServicos", required = false)
  @NotNull
  @Digits(integer = 13, fraction = 2)
  @DecimalMin("0.01")
  private Double valorServicos;

  @Element(name = "ValorDeducoes", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double valorDeducoes;

  @Element(name = "ValorPis", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double valorPis;

  @Element(name = "ValorCofins", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double valorCofins;

  @Element(name = "ValorInss", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double valorInss;

  @Element(name = "ValorIr", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double valorIr;

  @Element(name = "ValorCsll", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double valorCsll;

  @Element(name = "OutrasRetencoes", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double outrasRetencoes;

  @Element(name = "ValorIss", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double valorIss;

  @Element(name = "Aliquota", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double aliquota;

  @Element(name="ValorLiquidoNfse", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double valorLiquidoNfse;

  @Element(name="BaseCalculo", required = false)
  @Digits(integer = 13, fraction = 2)
  private String baseCalculo;

  @Transient
  @NotNull
  @Digits(integer = 13, fraction = 2)
  private Double valorLiquido;

  @Transient
  @NotNull
  @Digits(integer = 13, fraction = 2)
  private Double valorIssRetido;

  @Element(name = "DescontoIncondicionado", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double descontoIncondicionado;

  @Element(name = "DescontoCondicionado", required = false)
  @Digits(integer = 13, fraction = 2)
  private Double descontoCondicionado;

  public RpsValoresBetha() {
  }

  public RpsValoresBetha(ValoresBuilder builder) {
    this.valorServicos = builder.getValorServicos();
    this.valorDeducoes = builder.getValorDeducoes();
    this.valorPis = builder.getValorPis();
    this.valorCofins = builder.getValorCofins();
    this.valorInss = builder.getValorInss();
    this.valorIr = builder.getValorIr();
    this.valorCsll = builder.getValorCsll();
    this.outrasRetencoes = builder.getOutrasRetencoes();
    this.aliquota = builder.getAliquota();
    this.descontoIncondicionado = builder.getDescontoIncondicionado();
    this.descontoCondicionado = builder.getDescontoCondicionado();

    if (builder.getIssRetido().equals(IssRetido.NAO)) {
      this.valorIss = DoubleUtil.arredondarDuasCasas(calcularIss());
    } else {
      this.valorIssRetido = DoubleUtil.arredondarDuasCasas(calcularIss());
    }
    this.valorLiquido = DoubleUtil.arredondarDuasCasas(calcularValorLiquido());
  }

  public Double getValorServicos() {
    return valorServicos;
  }

  public Double getValorDeducoes() {
    return valorDeducoes;
  }

  public Double getValorPis() {
    return valorPis;
  }

  public Double getValorCofins() {
    return valorCofins;
  }

  public Double getValorInss() {
    return valorInss;
  }

  public Double getValorIr() {
    return valorIr;
  }

  public Double getValorCsll() {
    return valorCsll;
  }

  public Double getOutrasRetencoes() {
    return outrasRetencoes;
  }

  public Double getValorIss() {
    return valorIss;
  }

  public Double getAliquota() {
    return aliquota;
  }

  public Double getDescontoIncondicionado() {
    return descontoIncondicionado;
  }

  public Double getDescontoCondicionado() {
    return descontoCondicionado;
  }

  public Double getValorIssRetido() {
    return valorIssRetido;
  }

  public Double getValorLiquido() {
    return valorLiquido;
  }

  private Double calcularIss() {
    if (this.valorServicos == null || this.aliquota == null)
    {
      return null;
    }
    return this.valorServicos * this.aliquota;
  }

  private Double calcularValorLiquido() {
    return this.valorServicos -
        (this.valorPis == null ? 0 : this.valorPis) -
        (this.valorCofins == null ? 0 : this.valorCofins) -
        (this.valorInss == null ? 0 : this.valorInss) -
        (this.valorIr == null ? 0 : this.valorIr) -
        (this.valorCsll == null ? 0 : this.valorCsll) -
        (this.outrasRetencoes == null ? 0 : this.outrasRetencoes) -
        (this.valorIssRetido == null ? 0 : this.valorIssRetido) -
        (this.descontoCondicionado == null ? 0 : this.descontoCondicionado) -
        (this.descontoIncondicionado == null ? 0 : this.descontoIncondicionado);
  }
}
