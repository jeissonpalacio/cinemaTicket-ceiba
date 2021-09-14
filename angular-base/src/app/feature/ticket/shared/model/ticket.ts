export class Ticket{
    id?:number;
    idClient?:number;
    amount?:number;
    idMovieProjector?:number;
    idSeats?:number[];

    constructor(id?:number,idClient?:number,amount?:number,idMovieProjector?:number,idSeats?:number[]){
        
        if(id){
            this.id=id;
        }
        this.idClient=idClient;
        this.amount=amount;
        this.idMovieProjector=idMovieProjector;
        this.idSeats=idSeats;

    }
    
}