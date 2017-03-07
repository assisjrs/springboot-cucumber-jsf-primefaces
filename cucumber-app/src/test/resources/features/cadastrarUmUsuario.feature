@txn
Feature: Cadastrar um usuario
  Scenario: Deve ser possivel cadastrar um usuario no sistema
    Given o nome do usuario as "Assis" e o email "francisco.melo@concrete.com.br"
    When eu incluir o usuario Assis com email francisco.melo@concrete.com.br
    Then A lista de usuarios deve ter 1 item
