Feature: Cadastro
  Scenario: Ao cadastrar u usuario no sistema o nome deve ser obrigatorio
    Given o nome do usuario as ""
    When eu cadastrar o usuario
    Then O usuario deve receber a seguinte mensagem de erro "Nome obrigatorio!"

  Scenario: Ao tentar cadastrar um usuario o email deve ser obrigatorio
    Given o nome do usuario como "Gustavo Oliveira" e email como ""
    When eu cadastrar o usuario com o email vazio
    Then O sistema deve exibir a mensagem de erro "Email obrigatorio!"
