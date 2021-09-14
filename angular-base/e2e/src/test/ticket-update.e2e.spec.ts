import { TicketUpdate } from './../page/ticket/ticket-update.po';
import { TicketPage } from '../page/ticket/ticket-create.po';
import { MoviePage } from '../page/movie/movie.po';
import { NavbarPage } from '../page/navbar/navbar.po';
import { AppPage } from '../app.po';

describe('workspace-project Producto', () => {
    let page: AppPage;
    let navBar: NavbarPage;
    let moviePage:MoviePage;
    let ticketPage:TicketPage;
    let ticketUpdate:TicketUpdate;

    beforeEach(() => {
        page = new AppPage();
        navBar = new NavbarPage();
        moviePage = new MoviePage();
        ticketPage = new TicketPage();
        ticketUpdate = new TicketUpdate();
    });

    it("the actor edit the ticket page",()=>{
        page.navigateTo();
        navBar.clickBottonMoviePage();
        moviePage.clickRouteLinkMovieList();
        moviePage.clickButtomBuy();
        ticketUpdate.navagiteToEditPage();
        ticketUpdate.sendKeyInputSearchId();
        ticketUpdate.clickUpgradeButton();
        ticketUpdate.clickUpdateButton();
        ticketUpdate.clickSelectSeat();
        ticketUpdate.clickSelectOption();
        ticketUpdate.clickSelectSave();
    })

});
