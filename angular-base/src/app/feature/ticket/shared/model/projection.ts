
 export class MovieProjector{
     id:number;
     movieProjection:Date;
     hourMovie:string;
     idMovie:number;
     idCinema:number;


     constructor(id:number,movieProjection:Date,hourMovie:string,idMovie:number,idCinema:number){
        this.id=id;
        this.movieProjection=movieProjection;
        this.hourMovie=hourMovie;
        this.idMovie=idMovie;
        this.idCinema=idCinema;
     }

 }