export class Movie{
    id:number;
    name:string;
    typeMovie:string;
    lenght:string;
    rating:number;

    constructor(id:number,name:string,typeMovie:string,lenght:string,rating:number){
        this.id= id;
        this.name= name;
        this.typeMovie = typeMovie;
        this.lenght = lenght;
        this.rating = rating;
    }
}