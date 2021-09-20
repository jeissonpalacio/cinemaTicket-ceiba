import { TicketDelete } from './../page/ticket/ticket-delete.po';
import { TicketPage } from '../page/ticket/ticket-create.po';
import { MoviePage } from '../page/movie/movie.po';
import { NavbarPage } from '../page/navbar/navbar.po';
import { AppPage } from '../app.po';

describe('workspace-project Producto', () => {
    let page: AppPage;
    let navBar: NavbarPage;
    let moviePage:MoviePage;
    let ticketPage:TicketPage;
    let ticketDelete:TicketDelete;

    beforeEach(() => {
        page = new AppPage();
        navBar = new NavbarPage();
        moviePage = new MoviePage();
        ticketPage = new TicketPage();
        ticketDelete = new TicketDelete();
    });

    it("the actor edit the ticket page",()=>{
        page.navigateTo();
        navBar.clickBottonMoviePage();
        moviePage.clickRouteLinkMovieList();
        moviePage.clickButtomBuy();
        ticketDelete.navigateToDeletePage();
        ticketDelete.sendKeyInputSearchId();
        ticketDelete.clickSearchListForDelete();
    })

});
