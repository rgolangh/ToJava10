import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

class MyListener extends JavaParserBaseListener {

    public TokenStreamRewriter rewriter;

    public MyListener(BufferedTokenStream tokens) {
        rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public void exitLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) {
        JavaParser.TypeTypeContext type = ctx.typeType();
        rewriter.replace(type.start, "var");

        // catch generic class declarations like Map<String, String>
        if (type.classOrInterfaceType() != null && type.classOrInterfaceType().typeArguments() != null) {
            // remove <type, type>
            rewriter.delete(type.classOrInterfaceType().start, type.classOrInterfaceType().stop);
            rewriter.insertBefore(type.start, "var");
        }

    }
}