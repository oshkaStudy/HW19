package steps;

import io.qameta.allure.Step;
import models.*;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static specifications.BasicSpec.*;
import static tests.TestData.*;

public class ApiBookStoreSteps {

    @Step("API. Удаление всех книг")
    public void deleteAllBooks() {

        given(baseRequestSpec)
                .queryParams("UserId", USERID)
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .delete(BOOKS_ENDPOINT)
                .then()
                .spec(responseSpecWithCode(204))
                .extract().response();
    }

    @Step("API. Добавление книги в профиль")
    public AddBooksResponseModel addBook() {

        AddBooksBodyModel bodyData = new AddBooksBodyModel(USERID, List.of(new IsbnModel(ISBN)));

        return given(baseRequestSpec)
              .header("Authorization", "Bearer " + TOKEN)
              .body(bodyData)
              .when()
              .post(BOOKS_ENDPOINT)
              .then()
              .spec(responseSpecWithCode(201))
              .extract().as(AddBooksResponseModel.class);
    }

    @Step("API. Проверка, что книга успешно добавлена")
    public void assertBookAdded(AddBooksResponseModel response) {
        assertThat(response.getBooks(), is(notNullValue()));
        assertThat(response.getBooks().size(), is(1));
        assertThat(response.getBooks().get(0).getIsbn(), is(ISBN));
    }

    @Step("API. Удаление одной книги")
    public void deleteOneBook() {

        DeleteOneBookBodyModel bodyData = new DeleteOneBookBodyModel(ISBN,USERID);

        given(baseRequestSpec)
                .body(bodyData)
                .header("Authorization", "Bearer " + TOKEN)
                .when()
                .delete(BOOK_ENDPOINT)
                .then()
                .spec(responseSpecWithCode(204))
                .extract().response();
    }



}
