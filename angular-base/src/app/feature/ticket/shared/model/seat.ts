export class Seat{
    id:number;
    number_seat:string;
    available:number;
    id_ticket:number;
    id_movie_projector:number;

    constructor(id:number,number_seat:string,available:number,id_ticket:number,id_movie_projector:number){
        this.id = id;
        this.number_seat = number_seat;
        this.available = available;
        this.id_ticket = id_ticket;
        this.id_movie_projector=id_movie_projector;
    }

}