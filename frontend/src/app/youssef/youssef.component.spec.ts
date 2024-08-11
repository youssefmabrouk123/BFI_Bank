import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YoussefComponent } from './youssef.component';

describe('YoussefComponent', () => {
  let component: YoussefComponent;
  let fixture: ComponentFixture<YoussefComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [YoussefComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(YoussefComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
