package ru.job4j.ood.lsp.thirdexample;

/* все условия базового класса НЕ сохранены в подклассе  */
public class CountExt extends Count {
    int num;

    @Override
    public void check(int i) {
        super.check(i);
    }

    @Override
    public void setNum(int i) {
        this.num = i;
    }
}
