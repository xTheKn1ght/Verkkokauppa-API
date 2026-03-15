# Verkkokauppa REST-API - Dokumentaatio

## Projektin Lyhyt Kuvaus
Tämä projekti on REST-API, joka toteuttaa pienen verkkokaupan backendin relaatiotietokantaa (MySQL) ja Spring Bootia hyödyntäen.

Tässä REST-API:ssa ei ole ihan kaikki toiminnallisuudet täydellisesti toteutettu. Esimerkiksi Admin ja Customer päätepisteet halusin tulevaisuutta ajatellen erotella, mutta ei ole vielä tarkistusta valmista.

Järjestelmä tukee:
- Admin-toimintoja: asiakkaiden, tilausten, tuotteiden, toimittajien ja osoitteiden hallinta.
- Asiakkaiden toimintoja: tilausten tekeminen, tuotteiden katselu, omien kontaktien ja osoitteiden hallinta.

Tärkeimmät toteutetut ominaisuudet tietokannassa ja API:ssa:
- CRUD-päätepisteet kaikille keskeisille resursseille: Customers, Ordrs, Products, Suppliers, Addresses, Contacts, Categories.
- Näkymät helpompaa raportointia varten: vähän varastossa olevat tuotteet näkymässä, tilaukset ja asiakkaat samassa näkymässä, tilastot tilauksista päivittäin..
- Liipaisimet (triggers): varmistavat varaston riittävyyden ja tallentavat tuotteiden muutokset.
- Indeksit ja täystekstihaku nopeuttavat kyselyitä.
- Transaktiot tilausten ja varaston päivityksissä.
- Versionhallinta/backup tuotteille (temporaaliominaisuutta vastaava).

## API-päätepisteet
### Contacts
Admin:
GET /admin/contacts - Palauttaa List<Contact> eli listaa kaikki kontaktit.
POST /admin/contacts - palauttaa juuri luodun kontakin tiedot JSON muodossa {"email":""....}
DELETE /admin/contacts/{id} palauttaa 204 No Content viestin jos ID:n perusteella poistui kontakti.
Customer:
POST /customer/contacts - palauttaa saman, minkä POST adminilla palauttaisi 200 OK viestin kanssa, mutta asiakkaan lisäämä kontakti.
### Customer-Addresses
Admin:
/admin/customer-addresses
GET POST PUT DELETE, palauttaa saman tyyppisesti kuin aiemmin toimii.
Customer:
/customer/customer-addresses, olisi ideana että hallitsisi vain omia, mutta mainitsin aiemmin, ei ole tarkistusta vielä.
### Customers
Admin:
GET /admin/customers
GET /admin/customers/{id}
POST /admin/customers
PUT /admin/customers/{id}
DELETE /admin/customers/{id}
Customer:
Ei ole mitään vielä, mutta olisi idea muuttaa vain omia tietoja.
### Orders
Admin:
Hallinta, status-päivitys ja poistaminen.
GET /admin/orders
PATCH /admin/orders/{id}/status
DELETE /admin/orders/{id}
Customer:
Luo tilauksia, lisää tilausrivejä, katselut ja poistot.
GET /customer/orders/
POST /customer/orders/
POST /customer/orders/{orderId}/items
DELETE /customers/orders/{orderId}/items/{productId}
GET /customers/orders/{orderId}/items
Tilausten lisääminen tarkistaa varaston riittävyyden triggereillä.
Tuotteiden varasto vähenee automaattisesti tilausrivien lisääminen jälkeen.
### ProductCategories ja Products
Admin:
Nämäkin toimii samalla periaatteella
GET /admin/categories
GET /admin/categories/{id}
POST /admin/categories
PUT /admin/categories/{id}
DELETE /admin/categories/{id}
Nämäkin toimii samalla periaatteella paitsi viimeinen, joka nostaa 10% kaikkien tuotteiden hintaa (helposti voi muuttaa siihen muuttujan, jonka avulla voi sitten valita %, mutta ei ollu aikaa toteuttaa)
GET /admin/products
GET /admin/products/{id}
POST /admin/products
PUT /admin/products/{id}
DELETE /admin/products/{id}
PATCH /admin/products/increase-prices

Customer:
Samalla periaatteella toimii, mutta pelkästään voi katsoa.
GET /customer/categories
GET /customer/categories/{id}
Sama juttu paitsi viimeisin eli tuo search avulla voi hakea tuotteen, jonka nimessä on joku sana tai kirjaimet sekä minStock tietyn verran tai minPrice.
GET /customer/products
GET /customer/products/{id}
GET /customer/products/search (EXAMPLE: /customer/products/search?name=handle&minStock=100)

### Views
Admin:
Näitä varten tietokannassa toteutin näkymät, jotka ovat view_low_stock_products, view_o_with_c ja view_order_statistics
GET /admin/views/low-stock Tämä näyttää kaikki tuotteet, joiden stock_quantity < 10
Orders ja customers yhdistetty yhteen tauluun helppoja hakuja varten:
GET /admin/views/orders-with-customers 
GET /admin/views/orders-with-customers/status/{status} 
GET /admin/views/orders-with-customers/customer/{customerId}
Päivittäinen tilausten määrä:
GET /admin/views/order-statistics

Nämä näkymät ovat pelkästään adminia varten, jotta saisi enemmän raportteja selville.

### Suppliers ja Supplier-Addresses

CRUD /admin/suppliers ja /admin/supplier-addresses toteutettu, jotka mahdollistavat tuotteiden liittämisen toimittajiin.

## Tietokantaominaisuudet, joita ei näe API:ssa
Tässä näkee kaikki SQL-rakenteet ja -lauseet, joita toteutuksessa käytettiin.
### Liipaisimet
Kaikkien liipaisinten idea on varmistaa, että ei tulisi muutoksia, jotka pilaisi tietokannan eheyttä.
- trg_check_stock_before_orderitem - estää tilausrivin lisäämisen, jos varastoa ei ole tarpeeksi:

```mysql
CREATE TRIGGER trg_check_stock_before_orderitem
    BEFORE INSERT ON orderitems
    FOR EACH ROW
BEGIN
    DECLARE current_stock INT;
    SELECT stock_quantity INTO current_stock
    FROM products
    WHERE id = NEW.product_id;

    IF current_stock < NEW.quantity THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Not enough stock available';
    END IF;
END;
```

- trg_reduce_stock - vähentää tuotteen varastoa tilausrivien lisäämisen jälkeen:

```mysql
CREATE TRIGGER trg_reduce_stock
AFTER INSERT ON orderitems
FOR EACH ROW
UPDATE products
SET stock_quantity = stock_quantity - NEW.quantity
WHERE id = NEW.product_id;
```

trg_backup_product - tallentaa edellisen version tuotteesta products_backup-tauluun ennen päivitystä:

```mysql
CREATE TRIGGER trg_backup_product
BEFORE UPDATE ON products
FOR EACH ROW
INSERT INTO products_backup
VALUES (OLD.id, OLD.name, OLD.description, OLD.price, OLD.stock_quantity, OLD.category_id, OLD.supplier_id);
```

### Näkymät
- view_low_stock_products - tuotteet, joiden varasto < 10;

```mysql
CREATE VIEW view_low_stock_products AS
SELECT id, name, description, price, stock_quantity, category_id, supplier_id, version
FROM products
WHERE stock_quantity < 10;
```

- view_o_with_c - tilaukset ja asiakkaat yhdistettynä:

```mysql
CREATE VIEW view_o_with_c AS
SELECT o.id AS order_id,
       o.order_date,
       o.delivery_date,
       o.status,
       c.id AS customer_id,
       c.first_name,
       c.last_name,
       c.email
FROM orders o
JOIN customers c ON o.customer_id = c.id;
```

- view_order_statistics - tilastot päivittäin:

```mysql
CREATE VIEW view_order_statistics AS
SELECT CAST(order_date AS DATE) AS order_day,
       COUNT(*) AS orders_count
FROM orders
GROUP BY CAST(order_date AS DATE);
```

### Indeksit ja rajoitteet
Kaikki vierasavaimet ovat indeksoitu.
- Vierasavaimet ja uniikit rajoitteet:

```mysql
ALTER TABLE customeraddresses
ADD CONSTRAINT fk_customeraddress_customer FOREIGN KEY (customer_id)
REFERENCES customers(id);

ALTER TABLE company_customer
ADD CONSTRAINT FK2cak516leahs69ip83lsb76ex FOREIGN KEY (id)
REFERENCES customers(id);
```
- Täystekstihaku, joka nopeuttaa sähköpostin hakua:

```mysql
FULLTEXT INDEX ft_customers_email (email) ON customers;
```

### Transaktiot
- Kaikki tilausten luomiseen liittyvät päivitykset (orders, orderitems, products.stock_quantity) ovat transaktionaalisia, eli joko kaikki onnistuvat tai kaikki peruutetaan.

### Temporaali / Backup
- products_backup -taulu säilyttää tuotteiden vanhan tilan ennen päivityksiä:

```mysql
CREATE TABLE products_backup (
  id INT NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  price DECIMAL(10,2) NOT NULL,
  stock_quantity INT NOT NULL,
  category_id INT,
  supplier_id INT
);
```

## Esimerkkipyynnöt

### Supplier luonti
```http request
POST /admin/suppliers
Content-Type: application/json

{
  "name": "Tech Supplies Oy",
  "contact_name": "Anna Virta",
  "phone": "+358401234567",
  "email": "anna@techsupplies.fi"
}
```
Vastaus:
```json
{
  "id": 102,
  "name": "Tech Supplies Oy",
  "contact_name": "Anna Virtanen",
  "phone": "+358401234567",
  "email": "anna@techsupplies.fi"
}
```
### Tilausrivin luonti (Customer)

```http request
POST /customer/orders/200001/items
Content-Type: application/json

{
  "product": { "id": 10 },
  "quantity": 2,
  "unit_price": 50.00
}
```
Jos varasto < määrä, pyyntö epäonnistuu (triggereiden tarkistus).

## Tietoturva ja tietokannan eheys
- Vierasavaimet varmistavat relaatiotietojen eheyden.
- Liipaisimet estävät negatiivisen varaston.
- Transaktiot varmistavat atomiset päivitykset tilausten ja varaston muutoksissa.

## Huomioita
- Kaikki päätepisteet palauttavat JSONia.
- Näkymät ovat read-only eli ainoastaan GET toimintoja on.
- Adminilla täydet CRUD-oikeudet ja asiakkailla rajoitetut toiminnot.
- Tietokannan optimoinnit ja liipaisimet varmistavat konsistenssin automaattisesti.

## Testaus
Kaikki on käsin testattu ja varmistettu, että toimii oikein käyttämällä Postmania.

## LAZY vai EAGER lataus?
JPA-entiteettien relaatiot on pääosin konfiguroitu käyttämään LAZY loading -strategiaa, jotta vältetään turhat tietokantakyselyt ja parannetaan API:n suorituskykyä.