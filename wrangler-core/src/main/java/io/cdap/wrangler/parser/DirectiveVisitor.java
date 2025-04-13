package io.cdap.wrangler.parser;

import io.cdap.wrangler.api.parser.Token;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.antlr.DirectivesParser;
import io.cdap.wrangler.antlr.DirectivesBaseVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class DirectiveVisitor extends DirectivesBaseVisitor<Token> {
    @Override
    public Token visitByteSizeArg(DirectivesParser.ByteSizeArgContext ctx) {
        if (ctx.BYTE_SIZE() == null) {
            throw new IllegalArgumentException("Byte size argument cannot be null");
        }
        try {
            return new ByteSize(ctx.BYTE_SIZE().getText());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid byte size format: " + ctx.getText(), e);
        }
    }

    @Override
    public Token visitTimeDurationArg(DirectivesParser.TimeDurationArgContext ctx) {
        if (ctx.TIME_DURATION() == null) {
            throw new IllegalArgumentException("Time duration argument cannot be null");
        }
        try {
            return new TimeDuration(ctx.TIME_DURATION().getText());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid time duration format: " + ctx.getText(), e);
        }
    }

    @Override
    public Token visitChildren(ParseTree node) {
        // Handle cases where we want to visit child nodes
        return super.visitChildren(node);
    }
}