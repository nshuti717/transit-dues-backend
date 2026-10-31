# TransitDues

TransitDues is a backend API for a moto-taxi cooperative. It replaces the paper dues ledger. A rider pays dues, a treasurer records the payment, and the payment moves through a status flow until it is posted and a receipt is written for it.

Built with Spring Boot, Spring Data JPA and PostgreSQL, for the Web Technology and Internet mid-term project.

## Student

- Name: NSHUTI SANO Delphin
- Student ID: 27903
- Project name: TransitDues

## What the project does

A rider makes a dues payment. A treasurer, who is in charge of a stage and can collect several dues types, records that payment. A payment starts as DECLARED, can move to VERIFIED, and then ends as either POSTED or REVERSED. POSTED and REVERSED are final, the payment cannot change status again after that. Once a payment is POSTED, the treasurer can write receipts for it, and a rider can later check their receipt history.

## Main entities

- **Rider** — the person who pays dues. Identified by a unique plate number.
- **Treasurer** — the person who records payments. Belongs to one stage and can hold several dues types.
- **Stage** — a place a treasurer is assigned to.
- **DuesType** — a kind of due, like Daily Contribution or Welfare Fund.
- **DuesPayment** — one payment: who paid, who recorded it, the date, the amount and the status.
- **Receipt** — written only after a payment is POSTED, proves the rider paid.

## What the project can do

- Full create, read, update and delete for every entity.
- Search riders by last name, payments by status, payments in a date range.
- Find treasurers by dues type, free stages, unused dues types, and the riders of a treasurer.
- Business rules: a payment cannot be saved with a past date or a duplicate date for the same treasurer; a payment's status can only move along the allowed path; a treasurer can gain a dues type or move to a new stage, but not take a stage that is already taken.
- Receipts: write a receipt for a posted payment, see a rider's receipt history, delete a receipt, and a payment with receipts cannot be deleted until they are removed.
- Reports: count of payments per status, and cancelling every payment a treasurer has on one day.
- Bonus: a dashboard summary, and dues payments shown page by page.

## How to run it

1. Install PostgreSQL and make sure it is running.
2. Create an empty database named `transit_dues`.
3. Open `src/main/resources/application.properties` and set your own PostgreSQL username and password if they are different from what is already there.
4. Open a terminal in the project folder and run:

```bash
mvn spring-boot:run
```

5. The API starts on `http://localhost:8080`. The tables are created automatically the first time it starts.

## Testing

A Postman collection is included at the root of this repository: `transit-dues-midterm.postman_collection.json`. Import it into Postman to see one request for every requirement (A2, B1, C4, D2, and so on).

## Project structure

```
src/main/java/auca/ac/transit_dues/
  domain/      the entities (Rider, Treasurer, Stage, DuesType, DuesPayment, Receipt, PaymentStatus)
  repository/  Spring Data interfaces, one per entity
  service/     the business logic, one per entity
  controller/  the REST endpoints, one per entity
```
