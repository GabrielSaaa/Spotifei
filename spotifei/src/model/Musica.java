package model;

public class Musica {
    private int id;
    private String nome;
    private String artista;
    private String genero;
    private boolean curtida;

    public Musica(String nome, String artista, String genero, boolean curtida) {
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
        this.curtida = curtida;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getArtista() { return artista; }
    public String getGenero() { return genero; }
    public boolean isCurtida() { return curtida; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setArtista(String artista) { this.artista = artista; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setCurtida(boolean curtida) { this.curtida = curtida; }
}
