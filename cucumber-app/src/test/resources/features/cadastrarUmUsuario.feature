@txn
Feature: Cadastrar um usuario
  Scenario: Deve ser possivel cadastrar um usuario no sistema
    Given o nome do usuario as "Assis" e o email "francisco.melo@concrete.com.br"
    When eu incluir o usuario Assis com email francisco.melo@concrete.com.br
    Then A lista de usuarios deve ter 1 item

  Scenario: Cadastrar um Usuario e verificar se existe
    Given o nome do usuario como "Olavo Castro" e email "olavo@concrete.com.br"
    When cadastrar o usuario
    Then verificar se o nome "Olavo Castro" e email "olavo@concrete.com.br" se encontra na lista
