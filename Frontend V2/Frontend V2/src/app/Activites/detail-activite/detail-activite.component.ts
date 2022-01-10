import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ActiviteServiceService } from '../Services/activite-service.service';
import { NotificationService } from 'src/app/notification.service';

@Component({
  selector: 'app-detail-activite',
  templateUrl: './detail-activite.component.html',
  styleUrls: ['./detail-activite.component.scss']
})
export class DetailActiviteComponent implements OnInit {

  title = 'toaster-not';
  id: any;
  activite: any;
  participantParActivite: any;
  responsableParActivite: any;
  participationsList: any = [];
  selected: any = [];


  constructor(
    private service: ActiviteServiceService,
    private route: ActivatedRoute,
    private notifyService : NotificationService,
    private router:Router,
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.detail(this.id).subscribe((data: any)=>{
      this.activite = data;
    })
    this.service.ResponsableParActivite(this.id).subscribe((data:any)=>{
      this.responsableParActivite = data;
      console.log(this.responsableParActivite);

    })
    this.service.ParticipantParActivite(this.id).subscribe((data:any)=>{
      this.participantParActivite = data;
    })
  }

  presence(index: any){
    if (this.participationsList.includes(index)){
      let i = this.participationsList.indexOf(index);
      this.participationsList.splice(i, 1);
    }
    else{
      this.participationsList.push(index);
    }
    console.log(this.participationsList);
  }

  //retirer
  retirer(data: any){
    this.service.retirerResponsable(data).subscribe((data: any)=>{
      window.location.reload();
      this.router.navigateByUrl('detail-activite/'+ this.activite.id_activite, {skipLocationChange: true}).then(()=>
      this.router.navigate(['detail-activite', this.activite.id_activite])); 
    })
  }

  addPresence(form:NgForm){
        
    // on parcours la liste participation
    for(let j =0; j<this.participationsList.length; j ++){

      // On verifie si le participant est coche present
      if(this.participationsList[j].presence == false){
        this.participationsList[j].presence = true;
        this.participationsList[j].heure_arriver = (new Date()).toLocaleTimeString();

        console.log("presence modifié",this.participationsList[j]);
        console.log("presence id",this.participationsList[j].id_participation);

        this.service.presenceParticipant(this.participationsList[j].id_participation, this.participationsList[j]).subscribe((data: any)=>{
           //message suucès toast;
             
             this.notifyService.showSuccess("Données enregistrée avec succès !!");

           //console.log("okkkkkkkkk");
        })
      }else{
         //message erreur toast;
         this.notifyService.showError("Cette données est deja enregistrée !!");
      }
    }
  }


}


