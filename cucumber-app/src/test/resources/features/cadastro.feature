Feature: Cadastro
  Scenario: Ao cadastrar u usuario no sistema o nome deve ser obrigatorio
    Given o nome do usuario as ""
    When eu cadastrar o usuario
    Then O usuario deve receber a seguinte mensagem de erro "Nome obrigatorio!"
