package pt.ul.fc;

import liquidjava.specification.StateRefinement;
import liquidjava.specification.StateSet;

/** Rever e integrar um pedido de integração (pull request). */
@StateSet({"rascunho", "emRevisao", "aprovado", "integrado", "fechado"})
public class PedidoIntegracao {

    @StateRefinement(to="rascunho()")
    public PedidoIntegracao() {}

    /** Integra o pedido (definitivo). */
    @StateRefinement(from="aprovado()", to="integrado()")
    public void integrar() {}

    /** Fecha o pedido sem o integrar (definitivo). */
    @StateRefinement(from="rascunho() || emRevisao()", to="fechado()")
    public void encerrar() {}

    /** Pede revisão do rascunho. */
    @StateRefinement(from="rascunho()", to="emRevisao()")
    public void pedirRevisao() {}

    /** Aprova o pedido. */
    @StateRefinement(from="emRevisao()", to="aprovado()")
    public void aprovar() {}

    /** Pede alterações; volta a rascunho. */
    @StateRefinement(from="emRevisao() || aprovado()", to="rascunho()")
    public void pedirAlteracoes() {}
}
