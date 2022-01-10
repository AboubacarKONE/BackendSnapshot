import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponsableCorbeilleComponent } from './responsable-corbeille.component';

describe('ResponsableCorbeilleComponent', () => {
  let component: ResponsableCorbeilleComponent;
  let fixture: ComponentFixture<ResponsableCorbeilleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResponsableCorbeilleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResponsableCorbeilleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
