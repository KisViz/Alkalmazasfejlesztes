# 09. gyakorlat

## cURL
man curl

Különféle protokollokon keresztüli üzenetküldés, pl. http

### GET

A get az alap metódus amit használ, így a get kérés küldése igen egyszerű:

```bash
curl "http://127.0.0.1:8080/api/find"
```
### GET paraméterek
Ugyanúgy az URL-ben adhatjuk meg.

```bash
curl "http://127.0.0.1:8080/api/find?uticel=Budapest"
```

### Cookie küldése
Most a GET kérésen mutatjuk be, mert az egyszerű, de ugyanígy kell megadni a többi kérésnél is!

Ha szeretnénk Cookie-kat is használni, akkor:
- a Cookie csak egy header, amiben extra adat található,így megadható headerként (-H)
```bash
curl "http://127.0.0.1:8080/api/find" -H "Cookie: felpanzio=true"
```
- vagy megadható külön kapcsolóval is (-b)
```bash
curl "http://127.0.0.1:8080/api/find" -b "felpanzio=true"
```

### POST

-X paraméterrel adható meg a metódus.
Adjuk meg, hogy JSON-t akarunk küldeni a már látott -H kapcsolóval.
A -d kapcsolóval tudunk küldeni a kérés törzsében adatot.

```bash
curl -X POST -H "Content-Type: application/json" -d '{"nev":"curltest", "uticel":"curlURI", "felpanzio":"true", "utasok":3, "ejszaka":2, "leiras":"Hosszu curl parameter"}' "http://127.0.0.1:8080/api/add"
```

### Cookie kiolvasás

Azt már láttuk, hogy Cookie-t hogyan kell küldeni, de azt nem látjuk, hogy mi lesz azzal a Cookie-val amit visszakapunk, azt hogyan tesztelhetjük.

-i kapcsolóval informatívabb kiíratást kapunk a válaszról, amiben a Cookiek is szerepelnek.

```bash
curl -X POST -H "Content-Type: application/json" -d '{"felpanzio":"true"}' -i 127.0.0.1:8080/api/preference
```



## Extra Órai feladat

1, Teszteljük a többi végpontot is cURL segítségével!

2, Vessük össze a cURL eredményeket és a desktopos eredményeket ha be van állítva Cookie és ha nincsen a 2 külön helyen egyidőben!

3, Készítsetek egy '/api/home' POST végpontot, ami Cookie-ban letárol egy uticél!

4, Készítsetek egy '/api/home' GET végpontot ami csak akkor működik, ha a Cookiek között megtalálható a 2. pontban megadott Cookie!

5, Teszteljük a végpontokat cURL segítségével!

6, Vessünk egy pillantást a core web dao megvalósítására, Java oldali Http kérés küldésre példa a teljesség igénye nélkül!
