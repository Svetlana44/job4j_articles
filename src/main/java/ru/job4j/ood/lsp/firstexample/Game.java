package ru.job4j.ood.lsp.firstexample;

/* увеличено предусловие, нужно больше фич  */
public class Game extends Prog {
    @Override
    public void act(int ficha) {
        if (ficha < 3) {
            throw new IllegalArgumentException("Need more fich.");
        }
    }
}
