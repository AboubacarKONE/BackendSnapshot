import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceuilCorbeilleComponent } from './acceuil-corbeille.component';

describe('AcceuilCorbeilleComponent', () => {
  let component: AcceuilCorbeilleComponent;
  let fixture: ComponentFixture<AcceuilCorbeilleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceuilCorbeilleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceuilCorbeilleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
