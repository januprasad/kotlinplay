package com.github.solid;

public class InhertanceTest {

}

 interface Additions{
    void sum();
}
interface Multiplication{
    void mul();
}

class Calc implements Additions, Multiplication{

    @Override
    public void sum() {

    }

    @Override
    public void mul() {

    }
}



