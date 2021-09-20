import { TicketPage } from '../page/ticket/ticket-create.po';
import { MoviePage } from '../page/movie/movie.po';
import { NavbarPage } from '../page/navbar/navbar.po';
import { AppPage } from '../app.po';

describe('workspace-project Producto', () => {
    let page: AppPage;
    let navBar: NavbarPage;
    let moviePage:MoviePage;
    let ticketPage:TicketPage;

    beforeEach(() => {
        page = new AppPage();
        navBar = new NavbarPage();
        moviePage = new MoviePage();
        ticketPage = new TicketPage();
    });

    it("the actor views the ticket page",()=>{
        page.navigateTo();
        navBar.clickBottonMoviePage();
        moviePage.clickRouteLinkMovieList();
        moviePage.clickButtomBuy();
        ticketPage.clickListElementMovieProjector();
        ticketPage.clickSelectActive();
        ticketPage.sendKeysInputClient();
        ticketPage.clickButtomCreate();
        ticketPage.succesTicketElement();
    })

});
