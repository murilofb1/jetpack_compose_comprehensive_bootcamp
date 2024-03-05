# JetTip App
Primeiro aplicativo desenvolvido durante o curso aonde o principal objetivo é utilizar os componentes básicos do jetpack compose para criar novos composables e reutilizar eles durante o projeto. O App é uma calculadora de gorgeta e ele possui:

* Um `Card` na parte de cima aonde mostra o total da conta dividido pelo número de pessoas
* O aplicativo consiste em um um `OutlinedTextField` aonde o usuário consegue inserir o valor total da conta
* Dois botões aonde ele pode aumentar ou diminuir a quantidade de pessoas que irá dividir a conta.
* E um `Slider` aonde ele consegue informar a porcentagem de gorgeta (que vai de 1 a 100)

### Ao final o projeto ficou dessa forma:
<p align ="center">
  <img src="https://github.com/murilofb1/jetpack_compose_comprehensive_bootcamp/assets/74936314/b401e7b1-c502-4b01-9749-2790622d4c76" width="200"/>
</p>


### Componentes vistos:
* `TextField`, `Button`, `Text` e componentes de layout como `Box`, `Surface`, `Column` e `Row`

## Funções que adicionei
* Adiciei `viewModel` ao projeto, separando a parte lógica da classe aonde ficam os componentes e mantendo o estado das variáveis mesmo quando ocorria mudança do tema do App (tema claro ou escuro)
* Controle simples de exceções aonde que se o usuário digitar ou colar um valor que não possa ser convertido, o sistema retorna uma mensagem informando que está invalido
    ```
    fun canConvertToFloat(str: String, errorCallback: (() -> Unit)? = null): Boolean {
        var isFloat = true
        try {
            str.toFloat()
        } catch (e: Exception) {
            errorCallback?.invoke() // Chamando a função caso tenha sido passada para executar algo caso dê erro 
            isFloat = false 
        }
        return isFloat
    }
     ```