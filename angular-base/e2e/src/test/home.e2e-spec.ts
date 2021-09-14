import { MoviePage } from '../page/movie/movie.po';
import { HomePage } from '../page/home/home.po';
import { NavbarPage } from '../page/navbar/navbar.po';
import { AppPage } from '../app.po';

describe('workspace-project Producto', () => {
    let page: AppPage;
    let navBar: NavbarPage;
    let homePage: HomePage;
    let moviePage:MoviePage;

    beforeEach(() => {
        page = new AppPage();
        navBar = new NavbarPage();
        homePage = new HomePage();
        moviePage = new MoviePage();
    });

    it('Deberia ver home page', () => {
        page.navigateTo();
        navBar.clickBottonHomePage();
        homePage.getPageHomeTitle();
        // Adicionamos las validaciones despues de la creación
        // expect(<>).toEqual(<>);
    });

    it("the actor views the movie page",()=>{
        page.navigateTo();
        navBar.clickBottonMoviePage();
        moviePage.clickRouteLinkMovieList();
        moviePage.verifyButtomBuy();
        moviePage.verifyLabelTime();
        moviePage.verifyLabelTimeMovie();
        moviePage.verifyMovieName();


    })

});
