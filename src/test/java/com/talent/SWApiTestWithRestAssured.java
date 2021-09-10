package com.talent;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
public class SWApiTestWithRestAssured {
    @Test
    public void requestAresourcesThenLimiteReturn(){

        BaseApiResponse baseApiResponse = RestAssured
                .given()
                .baseUri("https://swapi.dev/api" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "" +
                        "")
                .and()
                .queryParam("format", "json")
                .when()
                .get("/")
                .then()
                .log().all()
                .and().assertThat().statusCode(is(equalTo(200)))
                .and()
                .body("films", response -> notNullValue())
                .body("vehicles", response -> notNullValue())
                .body("people", response -> notNullValue())
                .body("starships", response -> notNullValue())
                .body("species", response -> notNullValue())
                .body("planets",response -> notNullValue())
                .and ().extract ().body ().as(BaseApiResponse.class);
        RestAssured
                .given()
                .queryParam("format", "json")
                .log().all()
                .when()
                .post(baseApiResponse.getFilms())
                .then()
                .log().all()
                .and()
                .assertThat()
                .statusCode(is(equalTo(405)));
        }
    private static class BaseApiResponse {
        private String films;
        private String vehicles;
        private String people;
        private String starships;
        private String species;
        private String planets;

        public String getFilms() {
            return films;
        }

        public String getVehicles() {
            return vehicles;
        }

        public String getPeople() {
            return people;
        }
        public String getStarships() {
            return starships;
        }

        public String getSpecies() {
            return species;
        }

        public String getPlanets() {
            return planets;
        }
    }
}
