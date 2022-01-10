import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { from } from 'rxjs';
import { ExerciceServiceService } from 'src/app/Exercices/Services/exercice-service.service';
import { Activite } from 'src/app/Models/Activite';
import { Exercice } from 'src/app/Models/Exercice';
import { ActiviteServiceService } from '../Services/activite-service.service';

@Component({
  selector: 'app-ajout-activite',
  templateUrl: './ajout-activite.component.html',
  styleUrls: ['./ajout-activite.component.scss']
})
export class AjoutActiviteComponent implements OnInit {
listeExo: any;
activite = new Activite();
idExo: any;
exercice: any;
erreur= '';
error= '';

  constructor(
    private service: ActiviteServiceService,
    private serviceExercice: ExerciceServiceService,
    private router: Router,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.serviceExercice.listeExercice().subscribe((data: any)=>{
      this.listeExo = data;
      console.log(this.listeExo);
    })
  }

  AjoutActivite(form:NgForm) {
    if(form.valid){
      if(form.value.dateDebut <= form.value.dateFin) {
        this.idExo = form.value['exercice'];
        this.serviceExercice.detailExercice(this.idExo).subscribe((data:any)=>{
          this.exercice =  data;
          this.activite.dateDebut = form.value['dateDebut'];
          this.activite.dateFin = form.value['dateFin'];
          // this.activite.etat = form.value['date_etat'];
          this.activite.libelle = form.value['libelle'];
          this.activite.type = form.value['type'];
          this.activite.etat = 'active';
          this.activite.exercice = this.exercice;
          if(this.exercice.date_debut <= form.value.dateDebut && this.exercice.date_fin >= form.value.dateFin){
            this.service.Ajout(this.activite).subscribe((data: any)=> {
              console.log(data);
              this.router.navigate(['ajout-activite-suite', data.id_activite]);
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
}
