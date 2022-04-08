package org.example.FunctionStuff;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class Function {
    private final Expression expression;
    private final String function;

    public Function(String expr) {
        this.function = expr;
        this.expression = new ExpressionBuilder(expr).variable("x").build();
    }

    public Double apply(Double x) {
        try {
            return expression.setVariable("x", x).evaluate();
        } catch (RuntimeException e) {
            return Double.NaN;
        }
    }


    @Override
    public String toString() {
        return this.function;
    }

}