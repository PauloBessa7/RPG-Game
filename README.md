# 🧙‍♂️ Sistema de RPG com Spring Boot

Este projeto é uma aplicação backend desenvolvida em Java utilizando o framework **Spring Boot**, com foco na gestão de **personagens** e **itens mágicos** em um mundo de RPG.

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Banco de dados com H2
- Swagger (Documentação da API)

## 📌 Funcionalidades

- Cadastro, listagem, atualização e remoção de **personagens** e **itens mágicos**.
- Associação entre personagens e itens.
- Regras de negócio aplicadas:
  - A **soma de poder e defesa** de um personagem não pode ultrapassar **10 pontos**.
  - Cada personagem pode possuir **vários itens**, exceto amuletos, que são **exclusivos** por personagem.
  - Impede associações inválidas ou duplicadas entre personagens e itens.

## 🧩 Estrutura do Projeto

```bash
src/
├── main/
│   ├── java/
│   │   └── com.example.rpg/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── RpgApplication.java
│   └── resources/
│       ├── application.properties
│       └── ...
```
# ⚙️ Como Rodar o Projeto
Clone o repositório:

```bash
git clone https://github.com/PauloBessa7/RPG-Game.git
cd RPG-Game
```
Rode o projeto com:

```bash
./mvnw spring-boot:run
Acesse http://localhost:8080/swagger-ui/ para testar os endpoints.
```

---

## 📜 Regras de Negócio

### ⚔️ Personagem

- **Distribuição de Atributos**:  
  Ao criar um personagem, ele possui **10 pontos** para distribuir entre **Força** e **Defesa**, conforme desejar (ex: 5/5, 6/4, 10/0).  
  - A soma de **Força + Defesa** nunca pode ultrapassar **10**.
  - Atributos de Força e Defesa **consideram os bônus vindos dos Itens Mágicos** do personagem.
  
- **Classes Permitidas**:  
  Apenas as seguintes classes são válidas:
  - Guerreiro
  - Mago
  - Arqueiro
  - Ladino
  - Bardo

- **Atributos do Personagem**:
  - Identificador
  - Nome
  - Nome Aventureiro
  - Classe
  - Level
  - Lista de Itens Mágicos
  - Força (com bônus de itens)
  - Defesa (com bônus de itens)

- **Amuleto**:  
  Um personagem só pode possuir **1 item mágico do tipo Amuleto**.

---

### 🪄 Item Mágico

- **Tipos Permitidos**:
  - Arma
  - Armadura
  - Amuleto

- **Regras por Tipo**:
  - **Arma**:  
    - Força: 1 a 10  
    - Defesa: obrigatoriamente **0**
  - **Armadura**:  
    - Defesa: 1 a 10  
    - Força: obrigatoriamente **0**
  - **Amuleto**:  
    - Pode ter valores em **Força e Defesa**, mas o personagem só pode ter **um único Amuleto**.

- **Validações**:
  - Os atributos **Força** e **Defesa** de um item devem ser entre **1 e 10**.
  - Não pode existir um item mágico com **Força = 0** e **Defesa = 0** ao mesmo tempo.

- **Atributos do Item Mágico**:
  - Identificador
  - Nome
  - Tipo (Arma, Armadura, Amuleto)
  - Força
  - Defesa

---

## 🔗 Endpoints (resumo)

### Personagem

| Método | Rota                                          | Descrição                                 |
|--------|-----------------------------------------------|-------------------------------------------|
| POST   | `/characters/create`                          | Cria um novo personagem                   |
| POST   | `/characters/update/{id}`                     | Atualiza o nome do personagem             |
| POST   | `/characters/add-magic-item/{charId}/{itemId}`| Associa um item mágico ao personagem      |
| GET    | `/characters/list`                            | Lista todos os personagens                |
| GET    | `/characters/list/{id}`                       | Detalhes de um personagem específico      |
| GET    | `/characters/list-magic-items/{id}`           | Lista os itens mágicos de um personagem   |
| GET    | `/characters/list-amulets/{id}`               | Lista o amuleto do personagem (se houver) |
| DELETE | `/characters/remove/{id}`                     | Remove um personagem                      |
| DELETE | `/characters/remove-magic-item/{cid}/{iid}`   | Remove um item mágico do personagem       |

### Item Mágico

| Método | Rota                         | Descrição                   |
|--------|------------------------------|-----------------------------|
| POST   | `/magic-items/create`        | Cria um item mágico         |
| GET    | `/magic-items/list`          | Lista todos os itens        |
| GET    | `/magic-items/list/{id}`     | Detalha um item específico  |

---

## 🧪 Swagger

A documentação da API pode ser acessada via Swagger com o projeto funcionando: http://localhost:8080/swagger-ui/index.html#/

---

## Modelos de requisição Json

- Utilize desses modelos para as requisições Post com entradas no body

### Post - /magic-items/create
```bash
{
    "name": "Fire Sword",
    "type": "AMULET", // Tipos disponíveis <WEAPON, ARMOR, AMULET>
    "power": 2,
    "defence": 2
}
// Resposta esperada
{
    "id": 1,
    "name": "Fire Sword",
    "type": "AMULET",
    "power": 2,
    "defence": 2
}
```

### Post - /characters/create
```bash
{
    "name": "Charlinhos",
    "namePlayer": "Tenebroso",
    "characterClass": "BARD",
    // Tipos de classe disponiveis <WARRIOR, MAGE, ARCHER, ROGUE, BARD>
    "level": 0,
    "power": 2,  //  0 < x < 10
    "defence": 2 // 0 < x < 10
}
// Resposta esperada
{
    "id": 1,
    "name": "Charlinhos",
    "namePlayer": "Tenebroso",
    "characterClass": "BARD",
    "level": 0,
    "power": 2,
    "defence": 2,
    "magicItems": []
}
```

### Post - /characters/update/{id}
```bash
{
    "name": "Charlinhoso"
}
// Resposta esperada
{
    "id": 1,
    "name": "Charlinhoso",
    "namePlayer": "Tenebroso",
    "characterClass": "BARD",
    "level": 0,
    "power": 2,
    "defence": 2,
    "magicItems": []
}
````

