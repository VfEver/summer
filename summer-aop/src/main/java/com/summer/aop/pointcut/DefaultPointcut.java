package com.summer.aop.pointcut;

/**
 * default pointcut
 * @author zys
 * @date 2018/03/07
 */
public class DefaultPointcut implements ClassMatcher, MethodMatcher, Pointcut {
    /**
     * pointcut name
     */
    private String pointcutName;
    /**
     * expression
     */
    private String expression;

    public String getPointcutName() {
        return pointcutName;
    }

    public void setPointcutName(String pointcutName) {
        this.pointcutName = pointcutName;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * use # to split.
     * no # means just match class.
     * @return
     */
    private String getMatchClassStr () {

        if (expression.contains("#")) {

            return expression.split("#")[0];
        } else {

            return expression;
        }
    }

    /**
     * use # to split.
     * no # means match all methods.
     * @return
     */
    private String getMatchMethod () {

        if (expression.contains("#")) {

            return expression.split("#")[1];
        } else {

            return "";
        }
    }

    @Override
    public boolean matchClass(String className) {

        if (getMatchClassStr().equals(className)) {

            return true;
        }
        return false;
    }

    @Override
    public boolean matchMethod(String methodName) {

        if (getMatchMethod().equals("") || getMatchMethod().equals(methodName)) {

            return true;
        }
        return false;
    }
}
