package com.uefs.gerenciadorparabibliotecas.model;

public class Pessoa {
    protected   String    nome;
    protected   String    endereco;
    protected   String    senha;
    protected   CategoriaFuncionario    nivelDeProvilegio;
    protected   String   numeroDeTelefone;
    protected   Integer   userID;



    //constructor
    public Pessoa (String novoNome, String novoEndereco, String novaSenha, CategoriaFuncionario novoNivelDeProvilegio,
                   String novoNumeroDeTelefone, Integer novoUserID) {
        this.nome               = novoNome;
        this.endereco           = novoEndereco;
        this.senha              = novaSenha;
        this.nivelDeProvilegio  = novoNivelDeProvilegio;
        this.numeroDeTelefone   = novoNumeroDeTelefone;
        this.userID             = novoUserID;

    }


    //getters
    public String getNome () { return nome; }
    public String getEndereco () { return endereco; }
    public String getSenha () { return senha; }
    public CategoriaFuncionario getNivelDeProvilegio () { return nivelDeProvilegio; }
    public String getNumeroDeTelefone () { return numeroDeTelefone; }
    public Integer getUserID () { return userID; }

    //setters
    public void setNome (String novoNome) { this.nome = novoNome; }
    public void setEndereco (String novoEndereco) { this.endereco = novoEndereco; }
    public void setSenha (String novaSenha) { this.senha = novaSenha; }
    public void setNivelDeProvilegio(CategoriaFuncionario nivelDeProvilegio) { this.nivelDeProvilegio = nivelDeProvilegio; }
    public void setNumeroDeTelefone(String numeroDeTelefone) { this.numeroDeTelefone = numeroDeTelefone; }
    public void setUserID(Integer userID) { this.userID = userID; }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", senha='" + senha + '\'' +
                ", nivelDeProvilegio=" + nivelDeProvilegio +
                ", numeroDeTelefone='" + numeroDeTelefone + '\'' +
                ", userID=" + userID +
                '}';
    }

    /*método de construtor padrão
    protected pessoa (String novoNome, String novoEndereco, String novaSenha, String novoNivelDeProvilegio, Integer novoNumeroDeTelefone, Integer novoUserID){
        this.nome               = novoNome;
        this.endereco           = novoEndereco;
        this.senha              = novaSenha;
        this.nivelDeProvilegio  = novoNivelDeProvilegio;
        this.numeroDeTelefone   = novoNumeroDeTelefone;
        this.userID             = novoUserID;
    }
    //Criação de objeto de 'forma alternativa', cada método é completamente independerte um do outro, coleta o input do usuário e faz a alteração ele mesmo
    // Esses métodos provavelmente vão para o DAO!
    protected void mudarNome () {
        Scanner scanner     = new Scanner(System.in);
        System.out.print    ("Me diga seu nome: \n");
        String novoNome     = scanner.nextLine();
        this.nome           = novoNome;
    }

    protected void mudarEndereco () {
        Scanner scanner         = new Scanner(System.in);
        System.out.print        ("Me diga seu endereco: \n");
        String novoEndereco     = scanner.nextLine();
        this.endereco           = novoEndereco;
    }

    protected void mudarSenha () {
        Scanner scanner      = new Scanner(System.in);
        System.out.print     ("Digite sua senha: \n");
        String novaSenha     = scanner.nextLine();
        this.senha           = novaSenha;
    }

    protected void mudarNumeroDeTelefone () {
        Scanner scanner         = new Scanner(System.in);
        System.out.print        ("Insira seu número de telefone: \n");
        int novoNumero          = Integer.parseInt(scanner.nextLine());
        this.numeroDeTelefone   = novoNumero;
    }

    protected void definirIDPessoa () {
        Integer contador = 0;
        while (Pessoa.mapaPessoas.containsKey(contador)) {
            contador++;
        }
        Pessoa.mapaPessoas.put(contador, this);
        this.userID = contador;
    }

    protected void definirPrivilegio () {
        Scanner scanner = new Scanner(System.in);
        System.out.print    ("Qual o nível de provilégio desse perfil? (1 para convidado, 2 para bibliotecario, 3 para administrador. \n");
        int privilegio      = Integer.parseInt(scanner.nextLine());

        while (privilegio != 1 && privilegio != 2 && privilegio != 3) {
            System.out.print ("Por favor insira uma permissão válida (1 para convidado, 2 para bibliotecario, 3 para administrador. \n");
            privilegio = Integer.parseInt(scanner.nextLine());
        }

        if (privilegio == 1) {
            this.nivelDeProvilegio = "Convidado";
        }
        if (privilegio == 2) {
            this.nivelDeProvilegio = "Bibliotecario";
        }
        if (privilegio == 3) {
            this.nivelDeProvilegio = "Administrador";
        }
    }*/
}
