import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiviteCorbeilleComponent } from './activite-corbeille.component';

describe('ActiviteCorbeilleComponent', () => {
  let component: ActiviteCorbeilleComponent;
  let fixture: ComponentFixture<ActiviteCorbeilleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiviteCorbeilleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActiviteCorbeilleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
