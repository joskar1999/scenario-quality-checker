export interface Step {
  description: string;
  subSteps: Step []
}

export interface Scenario {
  title: string;
  actors: string [];
  systemActors: string [];
  steps: Step [];
}
