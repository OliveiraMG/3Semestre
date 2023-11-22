# 🛠️ Tira Teima - DTO e Mappers

## 🎯 Objetivo

Finalizar a codificação para assegurar que os endpoints estejam operantes e eficazes.

## 📝 Instruções

1. A entidade está correta ✅ - Nenhuma alteração necessária.
2. O repositório está adequado ✅ - Nenhuma alteração necessária.
3. O serviço precisa de ajustes 🔧.
4. O controlador precisa de modificações 🔨.
5. As DTOs estão presentes, mas vazias 📄 - Preenchimento requerido.
6. O mapper tem métodos definidos, mas sem implementação 🚧 - Implementação necessária.

## 🎮 Controlador
1. Deve retornar os status HTTP corretos, conforme discutido em aula 📚.

## 📊 DTOs
1. As DTOs devem corresponder às estruturas JSON a seguir 🧩.
2. A `UsuarioResponseDto` deve incluir um campo calculado 🧮 (campo virtual) que apresenta a idade do usuário.

## 🛑 Validações Necessárias (Bean Validation -> DTO de Criação)
1. **Nome**: Este campo não deve estar vazio ou ser apenas espaços em branco. Deve conter caracteres que formem um nome válido.
2. **Sobrenome**: Semelhante ao nome, este campo também não deve estar vazio ou conter apenas espaços em branco, assegurando que um sobrenome válido seja fornecido.
3. **CPF**: O número do CPF precisa ser um valor válido conforme o padrão brasileiro para Cadastro de Pessoas Físicas. Isso inclui tanto a formatação correta quanto a validade dos dígitos verificadores.
4. **E-mail**: O endereço de e-mail fornecido deve estar em um formato válido, garantindo que a correspondência eletrônica possa ser feita sem problemas.
5. **Data de Nascimento**: A data de nascimento deve representar um tempo no passado, não permitindo datas futuras. Isso assegura a validade da idade informada.

## Exemplos de Requisições:

### 1. Endpoint: GET - `http://localhost:8080/usuarios` 🌐

```json
[
  {
    "id": 1,
    "nomeCompleto": "João Silva",
    "documento": "12345678900",
    "dataNascimento": "1990-05-21",
    "contato": "joao.silva@email.com",
    "idade": 33
  }
]
```

### 2. Endpoint: GET - `http://localhost:8080/usuarios/1` 🌐

```json
{
  "id": 1,
  "nomeCompleto": "João Silva",
  "documento": "12345678900",
  "dataNascimento": "1990-05-21",
  "contato": "joao.silva@email.com",
  "idade": 33
}
```

### 3. Endpoint: GET - `http://localhost:8080/usuarios/resumo` 🌐

```json
[
  {
    "id": 1,
    "nomeCompleto": "João Silva"
  }
]
```

### 4. Endpoint: POST - `http://localhost:8080/usuarios` 🌐

```json
{
  "nome": "Ana",
  "sobrenome": "Pereira",
  "cpf": "123.456.789-09",
  "email": "ana.pereira@example.com",
  "dataNascimento": "1985-04-12"
}
```
### Resposta ao POST 📬:
```json
{
  "id": 2,
  "nomeCompleto": "Ana Pereira",
  "documento": "12345678909",
  "dataNascimento": "1985-04-12",
  "contato": "ana.pereira@example.com",
  "idade": 38
}
```