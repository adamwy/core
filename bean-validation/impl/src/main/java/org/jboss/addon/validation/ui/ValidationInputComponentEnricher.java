/**
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.addon.validation.ui;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.validation.Validator;

import org.jboss.forge.addon.ui.UICommand;
import org.jboss.forge.addon.ui.UIValidator;
import org.jboss.forge.addon.ui.input.InputComponent;
import org.jboss.forge.addon.ui.input.InputComponentInjectionEnricher;

/**
 * Enables Bean Validation 1.1 as a {@link UIValidator} object in the {@link InputComponent}
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class ValidationInputComponentEnricher implements InputComponentInjectionEnricher
{
   @Inject
   private Validator validator;

   @SuppressWarnings("unchecked")
   @Override
   public void enrich(InjectionPoint injectionPoint, InputComponent<?, ?> input)
   {
      Class<?> beanType = injectionPoint.getBean().getBeanClass();
      UIValidationAdapter adapter = new UIValidationAdapter(validator, input, (Class<UICommand>) beanType);
      input.addValidator(adapter);
   }
}
