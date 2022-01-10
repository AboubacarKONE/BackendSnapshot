import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ParticipationsServiceService {

  url= environment.URL;

  constructor(
    private http: HttpClient,
  ) { }

  //liste Participant
  listeparticipation(){
    return this.http.get(this.url + '/listeParticipation');
  }

  //Modifier Participant
  updateParticipations(id: any, part:any){
    return this.http.put(this.url + `/updateParticipation/${id}`, part);
  }



}
