import { Component, OnInit } from '@angular/core';
import { ParticipationsServiceService } from '../Services/participations-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ActiviteServiceService } from 'src/app/Activites/Services/activite-service.service';

@Component({
  selector: 'app-liste-participations',
  templateUrl: './liste-participations.component.html',
  styleUrls: ['./liste-participations.component.scss']
})
export class ListeParticipationsComponent implements OnInit {

  listparti: any;
  id_activite: any;
  participantParActivite: any;
  activite: any;
  presence: any = [];
  participationsList: any = [];
  p:any;
  participation={heure_arriver: '', presence: ''};
  searchText='';
  checked: any;
  //checkbox: any;


  constructor(
    public service:ParticipationsServiceService,
    public serviceAct:ActiviteServiceService,
    router:Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id_activite = this.route.snapshot.params['id'];
    
    
    this.serviceAct.detail(this.id_activite).subscribe((data: any)=>{
      this.activite = data;
    });

    this.serviceAct.ParticipantParActivite(this.id_activite).subscribe((data:any)=>{
      this.participantParActivite = data;
      // console.log("participant activite",this.participantParActivite);
        for(let i =0; i<this.participantParActivite.length; i ++){
          if(this.participantParActivite[i].presence == true){
            this.presence.push(this.participantParActivite[i]);
          }
          console.log("participant activite",this.presence);
        }
    })
  }

  /*
    async listeparti(){


    })
  }
  */


  /*
    presence(index: any){
    if (this.participationsList.includes(index)){
      let i = this.participationsList.indexOf(index);
      this.participationsList.splice(i, 1);
    }
    else{
      this.participationsList.push(index);
    }

    console.log(this.participationsList);

    let updateItem = this.listparti.find(this.findIndexToUpdate);
    console.log(JSON.stringify(updateItem.presence));
    //let indexx = this.listparti.indexOf(updateItem);
    //console.log(JSON.stringify(indexx));
    //this.listparti[indexx] = true;

  }
  */

  /*
  findIndexToUpdate(p:any) {
    return p ;
  }
  */
}