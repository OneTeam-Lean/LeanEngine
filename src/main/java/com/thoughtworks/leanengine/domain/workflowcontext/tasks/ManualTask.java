package com.thoughtworks.leanengine.domain.workflowcontext.tasks;

import com.thoughtworks.leanengine.domain.workflowcontext.interfaces.Activity;
import lombok.Data;

@Data
public class ManualTask extends Activity {
  private String triggeredBy;
}
