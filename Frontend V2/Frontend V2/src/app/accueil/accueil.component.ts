import { Component, OnInit } from '@angular/core';
import { ActiviteServiceService } from '../Activites/Services/activite-service.service';
import { ExerciceServiceService } from '../Exercices/Services/exercice-service.service';
import { ParticipantServiceService } from '../Participants/Services/participant-service.service';
import { AccueilServiceService } from './accueil-service.service';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit {
  listeExo : any;
  listpart : any;
  partTrouve : any = [];
  searchText: any;
  searchLibelle: any;
  listeAct : any;
  activites : any = [];
  listes : any;
  liste : any;
  nbreActivite = 0;
  nbreParticipant = 0;
  public totalHomme: any;
  public totalFemme: any;
  constructor(
    private mService: AccueilServiceService,
    private  acservice : ActiviteServiceService,
    private  exerciceService : ExerciceServiceService,
    private partService: ParticipantServiceService
    ) { }

  ngOnInit(): void {
    
    this.ActiviteOfDate();
    this.mService.getHomme().subscribe(
      (result) => {
        this.totalHomme = result
      }
    );
    this.totalFemme = this.mService.getFemme().subscribe(
      (result) => {
        this.totalHomme = result
      }
    );
    console.log(this.totalHomme);
    console.log(this.totalFemme);

    this.exerciceService.listeExercice().subscribe((data: any)=>{
        this.listeExo = data;
    })
  }

  ActiviteOfDate(){
    return this.acservice.getAllList().subscribe((data)=>{
      this.listeAct = data;
    })
  }

   getMonth(date:any) {
    this.acservice.Month(date.mois).subscribe((data)=> {
      this.listes = data;
      console.log(data);
    })
  }

  
  statExo(event:any){
    this.activites=[];
    this.nbreActivite = 0;
    this.nbreParticipant = 0;
    this.partTrouve = [];
    console.log("ok", event.target.value);
    this.acservice.getAllList().subscribe((data: any)=>{
      console.log();
      for(let i=0; i<data.length; i++){
        if(data[i].exercice.id == event.target.value){
          this.activites.push(data[i]);
          this.nbreActivite = this.nbreActivite +1;
        }
      }

      this.partService.listeparticipation().subscribe((dat:any)=>{
        for(let i=0; i<dat.length; i++){
          for(let j=0; j<this.activites.length; j++){
            if(dat[i].activite.id_activite == this.activites[j].id_activite){
              this.partTrouve.push(dat[i]);
              this.nbreParticipant = this.nbreParticipant +1;
          }
          }
         
        }
      })

      console.log("part",this.partTrouve);
      console.log("okkk",this.activites);
    })
    
  }


}