import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ExerciceServiceService } from 'src/app/Exercices/Services/exercice-service.service';
import { Activite } from 'src/app/Models/Activite';
import { ActiviteServiceService } from '../Services/activite-service.service';

@Component({
  selector: 'app-modifier-activite',
  templateUrl: './modifier-activite.component.html',
  styleUrls: ['./modifier-activite.component.scss']
})
export class ModifierActiviteComponent implements OnInit {
  id: any;
  activite = new Activite();
  idExo: any;
  exercice: any;
  listeExo: any;

   constructor(
    private service: ActiviteServiceService, 
    private activatedRoute: ActivatedRoute,
    private serviceExercice: ExerciceServiceService,
    private route : Router,
    private toast: ToastrService
   ) { }

   ngOnInit(): void {
     this.id = this.activatedRoute.snapshot.params['id'];
      this.service.detail(this.id).subscribe((data: any)=>{
        this.activite = data;
        console.log(this.activite);
        this.serviceExercice.listeExercice().subscribe((data: any)=>{
          this.listeExo = data;
          console.log(this.listeExo);
        })
      })
   }

  updateActivite(form:NgForm){
    if(form.valid) {
      if(form.value.dateDebut <= form.value.dateFin) {
        this.idExo = form.value['exercice'];
        this.serviceExercice.detailExercice(this.idExo).subscribe((data:any)=> {
          this.exercice =  data;
          this.activite.dateDebut = form.value['dateDebut'];
          this.activite.dateFin = form.value['dateFin'];
          this.activite.libelle = form.value['libelle'];
          this.activite.type = form.value['type'];
          this.activite.etat = form.value['etat'];
          this.activite.exercice = this.exercice;
          if(this.exercice.date_debut <= form.value.dateDebut && this.exercice.date_fin >= form.value.dateFin){
            this.service.update(this.id, this.activite).subscribe((res)=> {
              console.log(res);
              this.route.navigateByUrl('/liste-activite');
              this.confirm();
            })
          }else{
            this.showToas();
          }
          
        })
      }
      else{
        this.showToast();
    }
  }
    }

    showToast() {
      this.toast.error('La date de debut doit être inferieure à la date de fin')
    }
    showToas() {
      this.toast.error("Les dates de l'activité doivent être dans l'année de l'exercice")
    }
    confirm(){
      this.toast.success('Activité modifiée avec succès')
    }
  }
