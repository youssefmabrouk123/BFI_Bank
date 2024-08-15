import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FelicitationComponent } from './felicitation.component';

describe('FelicitationComponent', () => {
  let component: FelicitationComponent;
  let fixture: ComponentFixture<FelicitationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FelicitationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FelicitationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
