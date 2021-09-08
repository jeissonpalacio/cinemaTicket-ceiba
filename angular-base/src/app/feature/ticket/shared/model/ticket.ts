export class Ticket{
    idClient:number;
    amount:number;
    idMovieProjector:number;
    idSeats:number[];

    constructor(idClient:number,amount:number,idMovieProjector:number,idSeats:number[]){
        this.idClient=idClient;
        this.amount=amount;
        this.idMovieProjector=idMovieProjector;
        this.idSeats=idSeats;

    }
}