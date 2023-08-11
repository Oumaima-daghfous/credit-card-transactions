# Credit Card Transactions
## Description
Create a Spring Boot API with pagination, sorting and filtering to manage credit card transactions using a mock repository layer with the above JSON mock data.


## Installation
1) Clone the project

```
git clone https://github.com/Oumaima-daghfous/credit-card-transactions.git
```

2) Install dependencies

```
mvn install
```



## Tasks

- Get list of transactions 

```
http://localhost:8080/api/transactions?[attributes]
```
Attributes:
* amount: filter by amount
* merchant: filter by merchant
* status: filter by status
* sortBy: sorting transaction by amount, merchant,status (default value = "id")
* page: page number (default value = 0)
* size: items per page (default value = 10)