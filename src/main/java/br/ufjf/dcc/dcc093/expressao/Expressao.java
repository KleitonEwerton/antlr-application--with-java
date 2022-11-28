package br.ufjf.dcc.dcc093.expressao;

import br.ufjf.dcc.dcc093.expressao.antlr.grammar.TypeScript.MeuVisitortypeScript;
import br.ufjf.dcc.dcc093.expressao.antlr.grammar.TypeScript.output.TypeScriptLexer;
import br.ufjf.dcc.dcc093.expressao.antlr.grammar.TypeScript.output.TypeScriptParser;

import org.antlr.v4.runtime.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFileChooser;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author gleiph
 */
public class Expressao {

    public static JFileChooser fc = new JFileChooser();
    private static boolean printTree = false;
    public static String repositoryName = "";

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            if (args.length > 1 && args[1].equals("-tree"))
                printTree = true;

            downloadGitHub(args[0]);
            if (!repositoryName.equals(""))
                listFoundersInDirectory(repositoryName);

        } else {
            System.out.println("Usage: java -jar <url> [-tree (optional)]");
            System.exit(0);
        }
    }

    public static void downloadGitHub(String url) {

        String[] command = { "git", "clone", url };
        String[] urlSplit = url.split("/");

        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            Process p = pb.start();

            p.waitFor();

            if (p.exitValue() == 0) {
                System.out.println("Downloaded successfully!");

            } else {
                System.out.println("Error while downloading, folder project already exists!");
            }

            repositoryName = urlSplit[urlSplit.length - 1].split(".git")[0];

        } catch (IOException | InterruptedException ex) {
            System.out.println("Error while downloading or folder project already exists!");
        }

    }

    public static void listFoundersInDirectory(String directoryName) throws IOException {

        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        Arrays.sort(fList);

        for (File file : fList) {
            if (file.isDirectory())
                listFoundersInDirectory(file.getAbsolutePath());
            else if (file.isFile())
                if (file.getName().endsWith(".ts"))
                    ts(file.getAbsolutePath());
        }
    }

    public static void ts(String filePath) throws IOException {
        System.out.println("\n\n------------------------------------------------------------------------\n\n");

        System.out.println("Entrando no arquivo: " + filePath);
        CharStream charContent = CharStreams.fromFileName(filePath);

        TypeScriptLexer lexer = new TypeScriptLexer(charContent);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TypeScriptParser parser = new TypeScriptParser(tokens);

        ParseTree tree;

        tree = parser.program();

        if (printTree) {
            TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
            viewer.open();
        }

        MeuVisitortypeScript visitor = new MeuVisitortypeScript();
        visitor.visit(tree);
    }
}
