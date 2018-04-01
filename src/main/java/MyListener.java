import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

class MyListener extends JavaBaseListener {

    public TokenStreamRewriter rewriter;

    public MyListener(BufferedTokenStream tokens) {
        rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public void exitLocalVariableDeclaration(JavaParser.LocalVariableDeclarationContext ctx) {
        rewriter.replace(ctx.type().start, "var");
    }
}