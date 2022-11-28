package br.ufjf.dcc.dcc093.expressao.antlr.grammar.TypeScript;

import br.ufjf.dcc.dcc093.expressao.antlr.grammar.TypeScript.output.TypeScriptParser;
import br.ufjf.dcc.dcc093.expressao.antlr.grammar.TypeScript.output.TypeScriptParserBaseVisitor;

public class MeuVisitortypeScript extends TypeScriptParserBaseVisitor<Object> {

    @Override
    public Object visitFunctionDeclaration(TypeScriptParser.FunctionDeclarationContext ctx) {
        int beginLine = ctx.start.getLine();
        int endLine = ctx.stop.getLine();

        System.out.println("\n\n------------------------------------------------------------------------");
        System.out.println("Function: " + ctx.Identifier().getText());
        System.out.println("Parametros:" + ctx.callSignature().getText());
        System.out.println("Iniciou na linha " + beginLine + " terminou na " + endLine);
        System.out.println("Quantidade de linhas: " + (endLine - beginLine + 1));

        return super.visitFunctionDeclaration(ctx); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    //Get methods
    @Override
    public Object visitClassElement(TypeScriptParser.ClassElementContext ctx) {
        System.out.println("\n\n------------------------------------------------------------------------");
        String[] infos = ctx.getText().split("\\(");
        System.out.println("Method " + infos[0]);
        System.out.println("Parametros: (" + infos[1].split("\\)")[0] + ')');
        return super.visitClassElement(ctx); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object visitMethodDeclarationExpression(TypeScriptParser.MethodDeclarationExpressionContext ctx) {
        int beginLine = ctx.start.getLine();
        int endLine = ctx.stop.getLine();

        System.out.println("Iniciou na linha " + beginLine + " terminou na " + endLine);
        System.out.println("Quantidade de linhas: " + (endLine - beginLine + 1));
        return super.visitMethodDeclarationExpression(ctx); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object visitVariableDeclarationList(TypeScriptParser.VariableDeclarationListContext ctx) {
        System.out.println("Variavel " + ctx.start.getText() + " declarada/modificada na linha " + ctx.start.getLine());
        return super.visitVariableDeclarationList(ctx); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    

}
