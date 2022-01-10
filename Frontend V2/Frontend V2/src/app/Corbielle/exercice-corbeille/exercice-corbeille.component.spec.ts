import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExerciceCorbeilleComponent } from './exercice-corbeille.component';

describe('ExerciceCorbeilleComponent', () => {
  let component: ExerciceCorbeilleComponent;
  let fixture: ComponentFixture<ExerciceCorbeilleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExerciceCorbeilleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExerciceCorbeilleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
