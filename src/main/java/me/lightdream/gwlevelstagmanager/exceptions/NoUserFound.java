package me.lightdream.gwlevelstagmanager.exceptions;

public class NoUserFound extends RuntimeException {
    public NoUserFound() {
        super("This user does not exist");
    }
}