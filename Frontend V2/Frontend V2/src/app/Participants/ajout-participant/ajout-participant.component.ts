import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ActiviteServiceService } from 'src/app/Activites/Services/activite-service.service';
import { ParticipantServiceService } from '../Services/participant-service.service';

@Component({
  selector: 'app-ajout-participant',
  templateUrl: './ajout-participant.component.html',
  styleUrls: ['./ajout-participant.component.scss']
})
export class AjoutParticipantComponent implements OnInit {
  participant:any;
  participantTrouve: any;
  id: any;
  activite: any;
  participation: any;
  participation2: any;
  emailParticipant: any = [];
  emailParticipantPart: any = [];
  errorPart1 = '';
  errorPart2 = '';
  listeParticipant: any;

  constructor(
    private toast: ToastrService,
    private service: ParticipantServiceService,
    private serviceAct: ActiviteServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.serviceAct.detail(this.id).subscribe((data: any)=>{
      this.activite = data;
      this.serviceAct.ParticipantParActivite(this.activite.id_activite).subscribe((datas: any)=>{
        for(let i = 0; i<datas.length; i ++){
          this.emailParticipantPart.push(datas[i].participant.email);
        }
      })
    });
  }

  onRegister(form: NgForm){
    
   

      //recuperation des participants
      this.service.listeparticipant().subscribe((datas: any)=>{
          this.listeParticipant = datas;

      //Verification si l'email de participant existe deja dans la table participant
      for(let i = 0; i<this.listeParticipant.length; i ++){
        if(this.listeParticipant[i].email == form.value['email']){
          this.errorPart1 =  "Ce participant existe déjà !";

          //on recupère les informations de l'apprenant trouvé
          this.participantTrouve = this.listeParticipant[i];
          }
        }

      //Verification si l'email de participant existe deja dans la table participation
      if(this.emailParticipantPart.includes(form.value['email'])){
        this.errorPart2 = "Ce participant est déjà affecté à cet activité !";        
      }

      
      if(this.errorPart2 != ''){
        this.toast.error( "Ce participant est déjà affecté à cet activité !");
        this.errorPart1 ='';
      }else{
        if(this.errorPart1 !=''){
          this.errorPart1 =  "Ce participant existe déjà !";
        }else{

          //si les  messages d'erreurs sont vides alors on enregistre l'apprenant et l'affecte à l'activité
          this.service.ajoutParticipant(form.value).subscribe((app: any) =>{
            this.participant = app;
          this.participation = {"participant": this.participant, "activite": this.activite};
          this.serviceAct.AjoutParticipation(this.participation).subscribe((data: any)=>{
          this.router.navigateByUrl('detail-activite/'+ this.activite.id_activite, {skipLocationChange: true}).then(()=>
          this.router.navigate(['detail-activite', this.activite.id_activite])); 
          })
            });  
        }
       }
      
    });
 
  }

  affecterParticipant(data: any){
    this.service.detailParticipant(data).subscribe((datas: any)=>{
      this.participation2 = {"participant": datas, "activite": this.activite};
      this.serviceAct.AjoutParticipation(this.participation2).subscribe((data: any)=>{
        this.router.navigateByUrl('detail-activite/'+ this.activite.id_activite, {skipLocationChange: true}).then(()=>
        this.router.navigate(['detail-activite', this.activite.id_activite])); 
      })
     })
  }

}
